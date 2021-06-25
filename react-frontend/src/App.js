import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListUserComponent from './components/ListUsersComponent';
import CreateUserComponent from './components/CreateUserComponent';
import HeaderComponent from './components/HeaderComponent';
import UpdateUserComponent from './components/UpdateUserComponent'
function App() {
  return (
    <div>
    <Router>
          <HeaderComponent />
            <div className="container">
                <Switch> 
                      <Route path = "/" exact component = {ListUserComponent}></Route>
                      <Route path = "/users" component = {ListUserComponent}></Route>
                      <Route path = "/add-user" component = {CreateUserComponent}></Route>
                      {/* <Route path = "/view-employee/:id" component = {ViewUserComponent}></Route> */}
                      <Route path = "/update-user/:id" component = {UpdateUserComponent}></Route>
                </Switch>
            </div>
          {/* <FooterComponent /> */}
    </Router>
</div>
  );
}

export default App;
