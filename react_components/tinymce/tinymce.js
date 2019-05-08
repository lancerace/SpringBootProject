var React = require('react');
var ReactDOM = require('react-dom');
var PropTypes = React.PropTypes;

var TinyMce = React.createClass({ 
  getInitialState: function(){
    return {fileName: ''};
    
  },
  getDefaultProps:function(){
    
    return{htmlMarkup:''};
  },
  componentWillReceiveProps:function(nextProps){
    
    console.log('htmlMarkup: '+ nextProps.htmlMarkup);  
    
    //doesnt resolve initial content that has no empty string
    (nextProps.htmlMarkup != '') 
    ?
    (
      console.log('insert'),
      tinymce.activeEditor.setContent(''),
      tinymce.activeEditor.insertContent(nextProps.htmlMarkup)
    )
    : 
    (
      console.log('empty'),
      tinymce.activeEditor.setContent('')
    )
  },
  txtChange:function(){
    console.log('changed');
    
  },
    componentDidMount : function(){      
    tinymce.init({
    selector: '#myTextarea',
    plugins: 'hr link preview image',
    toolbar: 'bold italic | alignleft aligncenter alignright alignjustify ' + '| bullist numlist outdent indent | mybutton hr link ' + '| removeformat preview | image',
    image_caption: true,
    image_title: true,
    width: 767.5,
    height: 500,
    plugin_preview_width: 767.5,
    menu: {
      edit: {
        title: 'Edit',
        items: 'undo redo | cut copy paste pastetext | selectall'
      },
      view: {
        title: 'View',
        items: 'preview'
      },
      format: {
        title: 'Format',
        items: 'bold italic underline strikethrough superscript subscript | formats | removeformat'
      },  
    },  
    //callback for browse button executed
    file_picker_callback: function(callback, value, meta) {
      console.log('filepickerCallBack invoked');    
      //handle when user click browse button in image dialog
      if (meta.filetype == 'image') {    
        console.log('if meta filetype==image invoked');     
        $('#tinymce-uploader').trigger('click');
        // 
        // this.uploadChange(callback,meta);
        
        $('#tinymce-uploader').on('change',function(){
          //variables
          var input, file, fr, img,formData,filename;  
          console.log('tinymce onchange'); 
          input = document.getElementById('tinymce-uploader');
          //some validation
          if (!input) {
            console.log("Um, couldn't find the imgfile element.");
          }
          else if (!input.files) {
            console.log("This browser doesn't seem to support the `files` property of file inputs.");
          }
          else if (!input.files[0]) {
            console.log("Please select a file before clicking Load");
          }
          else {      
          file = input.files[0];
          console.log(file);
          console.log(file.name);
          //trigger componentdidupdate()
          this.setState({fileName:file.name});
          //prepare formdata to post file to server
          formData = new FormData();
          //store input tinymce to input reference      
          formData.append('file',file);    
          formData.append('filename',file.name);
          //Do ajax call
          $.ajax({
            type: 'POST',
            url: '/diyinsurance/article/imageUpload',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data)
            { 
              console.log('location: '+ data.location);
              //invoke onChange , pass up to parent in virtual dom tree and update state,re-render dom.
              
              //This is the important part. This callback will tell TinyMCE the path of the uploaded image
              callback(data.location);
            }.bind(this)
          });

      }//end else
    }.bind(this));            
      }//end if (meta.filetype == 'image')    
    }.bind(this),
  //   ,  
  //   file_browser_callback: function(field_name, url, type, win) {
  //     console.log('file browser callback');
  //     console.log(field_name);
  //     console.log(type);
  //     console.log(win);
  //  console.log(win.document.getElementById(field_name).value);
 // },    
    //attach event handler here before tinymce is rendered
    setup: function (editor) {
      
      // //detect change on tinymce content but does not detect images,hence on'change needed
      // editor.on('input',function(e){
      //   this.props.onChange();
      // }.bind(this));  
      
    //editor onchange looptwice for image insert.probably because of extra interaction with image dialog
      editor.on('change',function(e){
        console.log('editronchange');
        this.props.onChange();
      }.bind(this));  
      
      editor.addButton('mybutton', {
        text: 'IMAGES',
        icon: false,
        onclick: function (e) {        
          console.log($(e.target));
          if($(e.target).prop("tagName") == 'BUTTON') {
            $('#tinymce-uploader').trigger('click');
          }
        }
      });
    }.bind(this)
  });
},
  render: function() {
    return (
      <div>  
      <input id="tinymce-uploader" type="file" name="pic" accept="image/*" style={{display:"none"}}/>
    <textarea ref="tinymce" type="text"
        id="myTextarea" className="form-control"/>
    </div>
    );
  }
});

module.exports = TinyMce;
