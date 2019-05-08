var React = require('react');
var PropTypes = React.PropTypes;
var TinyMce = require('../tinymce');
var DropDown = require('../dropdown');
var PublishDropDown = require('../publishdropdown');
var ThumbNailUpload = require('../simplefileupload');

var ModalBody  = React.createClass({
  handleTinyMceChange:function(e){
    this.props.handleTinyMceChange(e);
  },
  simpleFileUploadChange:function(event){
    this.props.handleSimpleFileUploadChange(event);
  },
  getDefaultProps:function(){
    return{    
      title:'',
      tagID:'',
      tagName:'',
      publish:'',
      thumbnail:'',
      htmlMarkup:'',
      keywords:'',
    }    
  },
  componentDidUpdate(prevProps){
    console.log('modalbody componentdidupdate');
    console.log(prevProps);
    //set focus related
    if(prevProps.title != "")
    {
      console.log('invoke focus');
      (this.props.keywords != prevProps.keywords) ? this.refs.keywordInput.focus() : this.refs.keywordInput.blur();
      (this.props.title != prevProps.title) ? this.refs.titleInput.focus() : this.refs.titleInput.blur();
    }
  },
  dropDownClick:function(tagID,tagName){
    
    this.props.handledropDownClick(tagID,tagName);
  },
  publishDropDownClick:function(id,value)
  {
    this.props.handlePublishDropDownClick(id,value);
  },
  handleTitleChange:function(event){  
    this.props.handleTitleChange(event.target.value);
  },
  keywordsChange:function(event){
   this.props.keywordsChange(event.target.value);
  },
  componentWillReceiveProps:function(nextProps){
    console.log('modalBody');
    //get article object
    console.log(nextProps);
  },
  render: function() {
    return (
      <div className="modal-body" key="modalBody" ref="modbody">
        <div className="form-group">
          <div className="row">
            <label
              className="col-md-1 col-xs-1 control-label"
              for="idLbl">Title:</label>
            <div className="col-md-10 col-xs-4">
              <input  
                ref="titleInput"
                type="text" className="form-control" id="mTitle"
                 placeholder="" 
                 value={this.props.title} 
                 onChange={this.handleTitleChange}
                 required/>
            </div>
          </div>
        </div>
        
        <div className="form-group">      
          <div className="row">
            <label
              className="col-md-1 col-xs-1 control-label"
              for="idLbl">Tag:</label>

            <div className="col-md-3 col-xs-4">

              <DropDown
                url={"/diyinsurance/article/getAllTags"}

                tagID={this.props.tagID}
                tagName={this.props.tagName}
                onClick={this.dropDownClick}/>

            </div>

            <label
              className="col-md-1 col-xs-1 control-label"
              for="idLbl">Publish:</label>
            <div className="col-md-3 col-xs-3">
              <PublishDropDown selectedText={this.props.publish}
              onClick={this.publishDropDownClick}
                 />
          </div>
        </div>
      </div>

      <div className="form-group">      
      <div className="row">        
      <label
      className="col-md-1 col-xs-1 control-label"
      for="idLbl">Thumb-<p>Nail:</p></label>      
      <div className="col-md-4 col-xs-2">
      
      <ThumbNailUpload
      fileuploadURL={this.props.fileuploadURL}
      ref="simpleFileUpload"
      articleID={this.props.articleID}
      thumbnail={this.props.thumbnail}
      simpleFileUploadChange={this.simpleFileUploadChange}
      />
    </div>
    </div>      
    </div>
    
    <div className="form-group">
    <div className="row">
    <label
    className="col-md-1 col-xs-1 control-label"
    for="idLbl">Summary:</label>
  <div className="col-md-8 col-xs-6">          
    <TinyMce
      ref="TinyMce"
      onChange={this.handleTinyMceChange}
      htmlMarkup={this.props.htmlMarkup}/>    
  </div>
</div>
</div>

<div className="form-group">
          <div className="row">
            <label
              className="col-md-1 col-xs-1 control-label"
              for="idLbl">Keywords:</label>

            <div className="col-md-10 col-xs-8">
              <input
                ref="keywordInput"
                type="text"
                id="keyWords"
                className="form-control"
                value={this.props.keywords}
                onChange={this.keywordsChange}
                required
                />
            </div>  
          </div>
        </div>      
      </div>
    );
  }
});

module.exports = ModalBody;
