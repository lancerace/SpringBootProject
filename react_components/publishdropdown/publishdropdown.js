var React = require('react');
var PropTypes = React.PropTypes;


var PublishDropDown = React.createClass({
  getDefaultProps: function() {
   return {
     selectedText: 'Select one'
   };
 },
  liItems: [
    {id:'1',value:'Yes'},
    {id:'0',value:'No'}
  ],
  _onclick:function(id,value){
  this.props.onClick(id,value);
  },
  componentWillReceiveProps:function(nextProps){
  },
  render:function(){  
    var liItems = this.liItems.map(function(object){
      return <li
        key={object.id}
        onClick={this._onclick.bind(this,object.id,object.value)}>
        <a>
          {object.value}
      </a>
      </li>
    }.bind(this));
  
    return(
      <div>
      <button
        className="btn btn-default dropdown-toggle"
        type="button"
        id="dropdownMenu1"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="true">
        <span id="publishLbl">
          {this.props.selectedText}
        </span>
        <span className="caret">

        </span>

      </button>
      <ul
        className="dropdown-menu"
        aria-labelledby="dropdownMenu1">
        {liItems}
      </ul>
    </div>    
    )}
});
module.exports= PublishDropDown;