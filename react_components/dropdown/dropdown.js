var React = require('react');
require('bootstrap');
var PropTypes = React.PropTypes;

var Dropdown = React.createClass({
  getDefaultProps:function(){
  
  return {  
    tagID:'0',
    tagName:'Select one'
  }
},
  getInitialState: function(){
    return{
      liItems:[],
    }
  },
  componentWillReceiveProps: function(nextProps){
    console.log('invoked dropdown componentWillReceiveProps');   
    console.log('tagID:  '+ nextProps.tagID);
    console.log('tagName: '+ nextProps.tagName);

  },
  componentDidMount:function(){

    $.post(this.props.url, {
    }, function() {
      // success here
    }, 'json').done(
      function(response) {
        this.setState({liItems:response});
        /*bind(this) to refer ajax.done() current object as React Component.
        *Else the context is within $.post in which 'this.setState is not referring to react component'
        */
      }.bind(this)).fail(function(){
        console.log('getAllTags post failed');
      });  
    },
    _onclick:function(tagID,tagName){
      /* all dynamically generated li with onclick function
      *  update dropdownlist
      *  store tagname and id to <Span data-id=tagID>tagname</span>
      */
      // var dlLbl = $('#dlText');
      // dlLbl.text(tagName);
      // dlLbl.data('id',tagID);
      
      this.props.onClick(tagID,tagName);

    },
    render: function() {
      var liItems = this.state.liItems.map(function(object){
        //bind tagID to onclick function . Documentation references : https://facebook.github.io/react/tips/communicate-between-components.html
        return <li
          key={object.mTagID}
          onClick={this._onclick.bind(this,object.mTagID,object.mTagName)}>
          <a>
            {object.mTagName}
        </a>
        </li>

      }.bind(this)
    );
    return (
      <div className="dropdown" ref="dropdwn">


        <button
          className="btn btn-default dropdown-toggle"
          type="button"
          id="dropdownMenu1"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="true">

{/*setSelected item from user interaction in this span*/}
          <span id="dlText" data-id={this.props.tagID} ref="selectedItem">
            {this.props.tagName}
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
    );
  }
});
module.exports = Dropdown;
