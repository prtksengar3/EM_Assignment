import React, { Component } from 'react';
import UserService from '../services/UserService';

class CreateUserComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            name: '',
            emailId: '',    
            mobilenum:'',
            state:'',
            photos:'',
            skills:{
                Java:false,
                SpringBoot:false,
                JavaScript:false,
                MySQL:false
            }
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changeMobileHandler = this.changeMobileHandler.bind(this);
        this.changeStateHandler = this.changeStateHandler.bind(this);
        this.changeSkillsHandler = this.changeSkillsHandler.bind(this);
        this.changeGenderHandler = this.changeGenderHandler.bind(this);
        this.changeImageHandler = this.changeImageHandler.bind(this);
        this.saveUser = this.saveUser.bind(this);
    }

    saveUser = (e) => {
        e.preventDefault();
        var skillsas = Object.keys(this.state.skills).filter((x)=>this.state.skills[x]);
        const data = new FormData() 
        data.append('image', this.state.photos)

        let user = {name: this.state.name, 
                    emailId: this.state.emailId,
                    mobilenum:this.state.mobilenum,
                    gender:this.state.gender,
                    state:this.state.state,
                    skills:skillsas.toString(),
                    // photos:data,
                    // image:data
                }
        console.log('user => ' + JSON.stringify(user));
        UserService.createUser(user,data).then(res =>{
            this.props.history.push('/users')
        });
    }

    cancel(){
        this.props.history.push('/users');
    }

    changeNameHandler= (event) => {
        this.setState({name: event.target.value});
    }

    changeEmailHandler= (event) => {
        this.setState({emailId: event.target.value});
    }

    changeMobileHandler = (event) =>{
        this.setState({mobilenum: event.target.value})
    }

    changeStateHandler = (event) =>{
        this.setState({state:event.target.value})
    }

    changeSkillsHandler = (event) =>{
        console.log(event.target.value);
        let state = this.state;
        state.skills[event.target.value] = event.target.checked;
        this.setState(state)
    }

    changeGenderHandler = (event) =>{
        console.log(event.target.value);
        this.setState({gender:event.target.value})
    }

    changeImageHandler = (event) =>{
        console.log(event.target.files[0]);
        let file = event.target.files[0]
        this.setState({photos:file})
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Add User</h3>
                                <div className = "card-body">
                                    <form encType="multipart/form-data">
                                        <div className = "form-group">
                                            <label> Full Name: </label>
                                            <input placeholder="First Name" required name="name" className="form-control" 
                                                value={this.state.name} onChange={this.changeNameHandler}/>
                                            </div>
                                        
                                        <div className = "form-group ">
                                            <label> Email Id: </label>
                                            <input type="email" placeholder="Email Address" required name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Mobile No. </label>
                                            <input placeholder="Mobile Number" required name="mobileno" className="form-control" 
                                                value={this.state.mobilenum} onChange={this.changeMobileHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> State&nbsp; &nbsp;</label>
                                                <select  name = "state" value ={this.state.state} onChange = {this.changeStateHandler}>
                                                <option hidden defaultValue>State</option>
                                                <option value="Uttar Pradesh">Uttar Pradesh</option>
                                                <option value="Harayana">Haryana</option>
                                                <option value="Rajasthan">Rajasthan</option>
                                                <option value="Punjab">Punjab</option>
                                            </select>
                                        </div>


                                        <div className = "form-group">
                                            <label> Gender &nbsp; &nbsp; 
                                            <label>Male&nbsp; &nbsp;
                                            <input type="radio" className="form-control" name="gender"
                                                value="Male" checked={this.state.gender==="Male"} 
                                                onChange={this.changeGenderHandler}/>
                                                </label>
                                                <label>Female
                                            <input type="radio" className="form-control" name="gender"
                                                value="Female" checked={this.state.gender==="Female"}
                                                onChange={this.changeGenderHandler}/>
                                                </label>
                                                </label>
                                        </div>

                                        <div className = "form-group">
                                            <label> Skills </label>
                                                <br/>
                                                <label>Java&nbsp;&nbsp;
                                            <input type="checkbox" name="skills" value="Java" className="form-control" checked={this.state.skills.Java} 
                                                 onChange={this.changeSkillsHandler}/>
                                                 </label>
                                                 <label>
                                                SpringBoot&nbsp;&nbsp;
                                            <input type="checkbox" name="skills" value="SpringBoot" className="form-control" checked={this.state.skills.SpringBoot}
                                                 onChange={this.changeSkillsHandler}/>
                                                 </label>
                                                 <label>
                                                JavaScript&nbsp;&nbsp;
                                            <input type="checkbox" name="skills" value="JavaScript" className="form-control" checked={this.state.skills.JavaScript}
                                                 onChange={this.changeSkillsHandler}/>
                                                 </label>
                                                 <label>
                                                MySQL&nbsp;&nbsp;
                                            <input type="checkbox" name="skills" value="MySQL" className="form-control" checked={this.state.skills.MySQL}
                                                 onChange={this.changeSkillsHandler}/>
                                                 </label>
                                                
                                                
                                        </div>
                                
                                        {/* <div className = "form-group">
                                            <label> Profile Image </label>
                                            <input type="File" className="form-control" name="file"
                                                onChange={this.changeImageHandler}/>
                                        </div> */}
                                        <div>
                                            <label>Photos: </label>
                                            <input type="file" name="image" accept="image/png, image/jpeg" onChange={this.changeImageHandler}/>
                                        </div>


                                        <button className="btn btn-success" onClick={this.saveUser}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        );
    }
}

export default CreateUserComponent;