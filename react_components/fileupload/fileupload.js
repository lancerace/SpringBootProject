var React = require('react');
var PropTypes = React.PropTypes;
var $ = require('jquery');
//require bootstrap fileinput not working in the meantime
require('bootstrap-fileinput-npm');
var FileUpload = React.createClass({

  opts:{
    allowedFileExtension:["jpg","png","gif"],
    allowedPreviewTypes: ['image','html','text'],
    showUpload:false,
    allowedFileYupes:['image'],
    showUploadedThumbs: false,
    uploadAsync:false,
    uploadUrl: '/diyinsurance/article/imageUpload',
    previewFileIcon: '<i class="glyphicon glyphicon-king"></i>',
    maxFileCount: 1,
    initialPreviewThumbTags:[
      {
        '{CUSTOM_TAG_NEW}': ' ',
        '{CUSTOM_TAG_INIT}': '<span class=\'custom-css\'>CUSTOM MARKUP</span>'
      }
    ],
    layoutTemplates:{actionUpload: ''},
    msgFilesTooMany:'One thumbnail picture per article'
  },  
  componentDidMount: function(){
    $(this.refs.fileupload).fileinput(this.opts);
  },
  render: function() {
    return(
      <div>
        
        <input
          id="thumbnailUpload"
          ref="fileupload"
          type="file"/>
        
      </div>
    );
  }
});

module.exports = FileUpload;