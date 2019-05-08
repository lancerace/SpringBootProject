var React = require('react');
var PropTypes = React.PropTypes;

var MenuItem  = React.createClass({
  render: function() {
    return (
      <li>
        <a href="#" value={this.props.value}>
          {this.props.text}
        </a>

      </li>
    );
  }
});






module.exports = MenuItem;
