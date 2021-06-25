import React, { Component } from 'react';
import UserService from '../services/UserService';

class ListUsersComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            users:[]
        }
        this.addUser = this.addUser.bind(this);
        this.editUser = this.editUser.bind(this);
    }

    componentDidMount(){
        UserService.getUsers().then((res)=>{
            this.setState({users:res.data})
        });
    }

    addUser(){
        this.props.history.push('/add-user');

    }

    editUser(id){
        this.props.history.push(`/update-user/${id}`)
    }
    render() {
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
                            this.state.users.map(
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
                </div>
            </div>
        );
    }
}

export default ListUsersComponent;