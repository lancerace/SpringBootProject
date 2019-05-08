var React = require('react');
var PropTypes = React.PropTypes;

var ModalHeader = React.createClass({
  render: function() {
    return (
      <div className="modal-header">
        <button
          type="button"
          className="close"
          data-dismiss="modal"
          aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 className="modal-title">
          {this.props.title}
        </h4>
    </div>
    );
  }
});


module.exports = ModalHeader;