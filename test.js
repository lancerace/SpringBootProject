'use strict';
var $ = require('jquery');
var React = require('react');
var ReactDOM = require('react-dom');
var PropTypes = React.PropTypes;
var Modal = require('react-bootstrap/lib/Modal');
var Button = require('react-bootstrap/lib/Button');


var App = React.createClass({
getInitialState:function(){
   return {show: true,
     text:'yoyo'
   };
 },
  render: function() {
    return (
      <div className="static-modal">
       <Modal show={this.state.show}>
         <Modal.Header>
           <Modal.Title><b>TESTING</b></Modal.Title>
         </Modal.Header>
   
         <Modal.Body>         
           <label>Test input:</label>
         <input type="text" value={this.state.text}/>
           
         </Modal.Body>
   
         <Modal.Footer>
           <Button>Close</Button>
           <Button bsStyle="primary">Save changes</Button>
         </Modal.Footer>
   
       </Modal>
     </div>
      
    );
  }
});
//

ReactDOM.render(
  <App/>,
document.getElementById('reactContainer')
);


