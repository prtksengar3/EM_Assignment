import React, { Component } from 'react';
import UserService from '../services/UserService';

class ListUsersComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            users:[],
            currentPage:1,
            usersPerPage:5
        }
        this.addUser = this.addUser.bind(this);
        this.editUser = this.editUser.bind(this);
    }

    componentDidMount(){
        UserService.getUsers(this.currentPage,this.usersPerPage,"id").then((res)=>{
            console.log(res.data);
            this.setState({users:res.data})
        });
    }

    addUser(){
        this.props.history.push('/add-user');

    }

    editUser(id){
        this.props.history.push(`/update-user/${id}`)
    }

    changePageHandler= (event) => {
        this.setState({currentPage: event.target.value});
        
        UserService.getUsers(this.currentPage,this.usersPerPage,"id").then((res)=>{
            this.setState({users:res.data})
        });
    }

    render() {
        const {users,currentPage,usersPerPage} = this.state;
        const lastIndex = currentPage * usersPerPage;
        const firstIndex = lastIndex - usersPerPage;
        const currentUsers = users.slice(firstIndex,lastIndex);
        const totalPages = users.length / usersPerPage;
        return (
            <div>
                <h2 className="text-center">Users List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addUser}>Add User</button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>Email Id</th>
                            <th>Mobile Number</th>
                            <th>Gender</th>
                            <th>State</th>
                            <th>Skills</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            users.map(
                                user=>
                                <tr key = {user.id}>
                                    <td>{user.name}</td>
                                    <td>{user.emailId}</td>
                                    <td>{user.mobilenum}</td>
                                    <td>{user.gender}</td>
                                    <td>{user.state}</td>
                                    <td>{user.skills}</td>
                                    <td>
                                        <button onClick={()=> this.editUser(user.id)} className="btn btn-info">Update</button>
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                    </table>
                    <nav className="d-flex justify-content-center">
                        <ul className="pagination">
                            <li className="page-link">1</li>
                            <li className="page-link" value={2} onClick={this.changePageHandler}>2</li>
                            <li className="page-link">3</li>

                        </ul>
                    </nav>
                </div>
            </div>
        );
    }
}

export default ListUsersComponent;