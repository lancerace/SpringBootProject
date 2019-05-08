var React = require('react');
var PropTypes = React.PropTypes;
var Modal = require('react-bootstrap/lib/Modal');
//testing on bootstrap in react environment
require('bootstrap');
var ModalInstance = React.createClass({
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
      show:false
    };
  },
  componentWillReceiveProps:function(nextProps){
    console.log('invoked');
    
    console.log('showbsmodal');
    if(nextProps.action==='edit')
    {     
      
      
      console.log('invoked onenter');
  
      
      
      console.log(nextProps);   
      console.log(nextProps.articleID);
    
    }//end if (this.state.action==="edit")
  },
  handleTitleChange: function(value){
      this.setState({title:value});
  },    
  render: function() {
    return (
      <Modal 
        show={this.props.show}
        >
        <Modal.Body>
          <FormGroup>        
            <label
              className="col-md-1 col-xs-1 control-label"
              for="idLbl">Title:</label>          
            <div className="col-md-10 col-xs-4">        
              <input              
                ref="titleInput"
                type="text"
                className="form-control"
                id="mTitle"
                placeholder=""              
                value={this.props.title}              
                onChange={this.handleTitleChange}
                required/>            
            </div>        
          </FormGroup>        
        </Modal.Body>
        <Modal.Footer>
        </Modal.Footer>      
      </Modal>      
    );
  }
});

var FormGroup = React.createClass({
  render: function() {
    return (
      <div className="row">    
        {this.props.children}
      </div>
    );
  }
});


module.exports = ModalInstance;