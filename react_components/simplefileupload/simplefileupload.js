var React = require('react');
var ReactDOM = require('react-dom');
var PropTypes = React.PropTypes;

var SimpleFileUpload = React.createClass({  
  simpleFileUploadChange: function(event){
    this.props.simpleFileUploadChange(event);
  },
  
  getDefaultProps: function() {
   return {
     thumbnailName: 'No file selected'
   };
  },
  browseClick:function(){
    this.refs.simpleFileUpload.click();
  },
  componentWillReceiveProps :function(nextProps){
  
  //if newly receieved props consists of fileuploadURL,upload file
  if(nextProps.fileuploadURL != ''){
    console.log('invoked simpleFileUpload willreceiveprops');  
    console.log(nextProps.articleID);
    console.log(nextProps.fileuploadURL);
    //upload file asynchronous
    //processData set false to include boundary string for image
    //sending image in the form of FormData is the simplest setup as additional configuration such as setting mime-type is not needed
    var formData = new FormData();
    var articleID= nextProps.articleID;
    formData.append('uploadfile',this.refs.simpleFileUpload.files[0]);
    formData.append('articleID',nextProps.articleID);
    $.ajax({
      url: nextProps.fileuploadURL,
      type: 'POST',
      data: formData,
      enctype: 'multipart/form-data',
      processData: false,
      contentType: false,
      cache: false
    }).done(function(response){
      console.log('success');
    }).fail(function(){
      console.log('failed');
    })//end ajax
  }
  },
  render: function() {
    return (
      <div>
      <button className="btn btn-primary" onClick={this.browseClick}>Browse</button>
      <input id="upload-file-input" ref="simpleFileUpload" type="file" name="uploadfile" accept="image/*"
       style={{display:'none'}} onChange={this.simpleFileUploadChange}
        />
      <span className="liftElement" style={{position:'relative', left:'10px'}}>
      {this.props.thumbnail}
      </span>
    </div>
    );
  }
});

module.exports = SimpleFileUpload;