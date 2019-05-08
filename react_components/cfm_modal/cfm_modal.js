var React = require('react');
var PropTypes = React.PropTypes;
var Modal = require('react-bootstrap/lib/Modal');
require('bootstrap');
require('notify')(window,$);

var ConfirmModal = React.createClass({
  onClick:function(e){
    console.log('cfmModal onclick');
    $.post(this.props.ajaxURL,{'mID':this.props.id}
    ,function(response){
      console.log('success');
      this.props.onHide();
      $.notify("Success","success");
    }.bind(this)
  )},
  onHide:function(e){
    this.props.onHide(e);
  },
  render: function() {
    return (
      
      <Modal show={this.props.show}>
      <Modal.Header>
        <Modal.Title><b>Confirmation</b></Modal.Title>
      </Modal.Header>

      <Modal.Body>      
      Are you sure?
      </Modal.Body>

      <Modal.Footer>
      <input type="button" className="btn btn-primary" value="Yes" onClick={this.onClick}/> 
      <input type="button" className="btn btn-default" value="No" onClick={this.onHide}/> 
      </Modal.Footer>
      </Modal>
    );
  }

});

module.exports = ConfirmModal;