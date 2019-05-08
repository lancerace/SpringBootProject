var React = require('react');
var PropTypes = React.PropTypes;

var ModalFooter = React.createClass({
  onSave:function(event){
    this.props.onSave(event);
  },
  render:function(){
    return(
      <div className="modal-footer">
        <input
          type="submit"
          className="btn btn-primary"
          onClick={this.props.onSave}
          value="Save"/>
        <input
          type="button"
          className="btn btn-default"
          value="Cancel"
          data-dismiss="modal"
          />
      </div>
    )
  }
});


module.exports = ModalFooter;



