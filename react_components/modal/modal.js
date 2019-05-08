var React = require('react');
var PropTypes = React.PropTypes;
var ModalHeader = require('./modalheader');
var ModalBody = require('./modalBody');
var ModalFooter = require('./modalFooter');

var Modal = React.createClass({
  getInitialState: function(){
    return {
      article:'',
      articleID:'',
      title:'',
      tagID:'',
      tagName:"Select one",
      publish:"Select one",
      //thumbnail stores the displayed thumbnail name,not path <img>
      thumbnail:"No file selected",
      summary:'',
      htmlMarkup:'',
      keywords:'',
      uploadURL:'',
      action:'',
    };
  },     
  componentWillReceiveProps:function(nextProps){
    console.log('invoked');
    
    console.log('showbsmodal');
    if(nextProps.action==='edit'){   
      console.log(nextProps);   
      console.log(nextProps.articleID);
      $.post('/diyinsurance/article/getOneArticle',
      {'mArticleID': nextProps.articleID},
      function(response){
        console.log('getoneArticle response:');
        console.log(response);
        
        this.setState({
          article: response,
          title: response.mTitle,
          tagID:response.mTagID,
          tagName:response.mTagName,
          publish:response.mPublish,
          summary:response.mSummary,
          thumbnail:response.mThumbnail,
          htmlMarkup:response.mHtmlMarkup,
          keywords: response.mKeywords,
          articleID: nextProps.articleID,
          action: nextProps.action
        });    
      }.bind(this));  
    }//end if (this.state.action==="edit")
  },
  //value retrieved from modalbody child component via onChange
  handleTitleChange: function(value){
      this.setState({title:value});
  
  },
  handlePublishDropDownClick : function(id,value){
    this.setState({
      publish:value});    
    },
  handleTinyMceChange:function(){
    console.log('active content:' +tinymce.activeEditor.getContent());
    this.setState({htmlMarkup: tinymce.get('myTextarea').getContent()});
  },
  handleKeywordsChange:function(value){
    this.setState({keywords:value});
  },
  handleSimpleFileUploadChange:function(event){
    console.log('modalboostrap body ')
    console.log(event.target.value);
    this.setState({thumbnail:event.target.value});
  },
  handledropDownClick:function(tagID,tagName){
    console.log('handledropDownClick: ' + tagID + ':' + tagName);
    this.setState({tagID:tagID,tagName:tagName});
  },
  componentDidMount: function(){
    // $(document).on('focusin', function(event) {
    //   if ($(event.target).closest(".mce-window").length) {
    //     e.stopImmediatePropagation();
    //   }
    // });
    console.log('compoentdidMount modal..');
    $(this.refs.mod).modal({show: false});
    //reset all input when modal is hidden
    $(this.refs.mod).on('hide.bs.modal', function (e){
      console.log('modal hide.bs.modal invoked');
          tinymce.activeEditor.setContent('');
      this.setState({
        title: '',
        tagID:'0',
        tagName:'Select one',
        publish:'Select one',
        thumbnail:'No file selected',
        htmlMarkup:'',
        keywords: '',
        articleID: '',
      });    
    
    }.bind(this));
    //reset tinymce textarea content with dom manipulation
  
  },
  hide : function(){  $(this.refs.mod).modal('hide');
  },
  onSubmitValidationCheck:function(){
    
    console.log('validation check');
    return false;
  },
  onSave: function(e){
    alert('onSave executed');  
      /*prepare object literal for ajax call
      *mSummary & mHtmlMarkup retrieve from tinymce.get() as it is DOM manipulation,not react 'VIRTUAL DOM'
      */
    var mArticle = {
     mArticleID: this.state.articleID,
     mTitle: this.state.title,
     mTagID: this.state.tagID,
     mTagName:this.state.tagName,
     mPublish:this.state.publish,
     mThumbnail:this.state.thumbnail,
     mSummary: tinymce.get('myTextarea').getContent({format : 'text'}),
     mHtmlMarkup: tinymce.get('myTextarea').getContent(),
     mKeywords:this.state.keywords
   };
   console.log(this.state.action);
   //to differentiate update 
   //add new Article,retrieve state.action value and compare  
   this.state.action ==='edit' ? (
     console.log('mArticle before sending..:'),
     console.log(mArticle),
     $.post('/diyinsurance/article/updateOneArticle',{
       'mArticle' : JSON.stringify(mArticle)
     },function(response){
       if(response>0){
         console.log('success updateOneArticle');
         //if response success,hide modal,notify user
         $(this.ref.mod).modal('hide');
         
         
         $.notify("Updated!","success");
         //upload thumbnail only if file existed in input type=file
         if($('#upload-file-input').val() !="")
         this.setState({
           'uploadURL':"/diyinsurance/article/uploadThumbnail"    
         });
       }
       else 
       {
         
         console.log("upload failed");
       }
     }.bind(this))
   ):(
     /*get <PublishDropDown> value;
     *saveOneArticle which the server will response with a newly created ArticleID
     *InsertOneArticle when user create new
     */
     $.post(this.props.onSaveUrl, {  
       'mArticle' : JSON.stringify(mArticle)
     },
     function(response){
       console.log('success');
       console.log('check articleid: '+ response);  
       //if response success,hide modal,notify user
       $(this.ref.mod).modal('hide'); 
       $.notify("Added!","success");      
       /*set articleID and uploadURL state,pass as props to <SimpleFileUpload>,
       *re-render and upload thumbnail based on uploadURL
       *setting state on uploadUrl will reflect changes on <SimpleFileUpload> to upload thumbnail
       *with a folder format of eg(diyinsurance/#articleID/'thumbnail.jpg')
       */ 
       this.setState({
         'articleID':response,
         'uploadURL':"/diyinsurance/article/uploadThumbnail"    
       });
     }.bind(this)
   ).fail(function(){
     console.log('getAllTags post failed');
   })
 )
 
 
 //update jquery dataTables + hide modal
 console.log('reloading dtable with ajax');
 // $('#dtables').DataTable().ajax.reload(function(){
 //   console.log('success  ajax reload');
 // });
},
render: function() {
    return (
      <form onSubmit={this.onSubmitValidationCheck}>
      <div
        className="modal fade bs-example-modal-lg"
        tabindex="-1"
        role="dialog"
        ref="mod"
        id="modal">
        <div className="modal-dialog modal-lg">
          <div className="modal-content">
            <ModalHeader title={this.props.headerTitle}/>

            <ModalBody ref="modBody"    
               title={this.state.title}
               tagID={this.state.tagID}
               tagName={this.state.tagName}
               publish={this.state.publish}
               thumbnail={this.state.thumbnail}
               htmlMarkup={this.state.htmlMarkup}
               keywords={this.state.keywords}          
               object={this.state.article}
               fileuploadURL={this.state.uploadURL} 
               articleID={this.state.articleID} 
               handleTitleChange={this.handleTitleChange}
               handleTinyMceChange={this.handleTinyMceChange}
               keywordsChange={this.handleKeywordsChange}
               handleSimpleFileUploadChange={this.handleSimpleFileUploadChange}
               handledropDownClick={this.handledropDownClick}
               handlePublishDropDownClick={this.handlePublishDropDownClick}
               />                        
             <ModalFooter onSave={this.onSave}/>
           </div>
         </div>
       </div>
     </form>
    );
  }
});

module.exports = Modal;
