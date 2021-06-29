import React, { Component } from 'react';
import UserService from '../services/UserService';

class UpdateUserComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id:this.props.match.params.id,
            name: '',
            emailId: '',
            mobilenum:'',
            state:'',
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
        this.updateUser = this.updateUser.bind(this);
    }

    updateUser = (e) => {
        e.preventDefault();
        var skillsas = Object.keys(this.state.skills).filter((x)=>this.state.skills[x]);
        let user = {name: this.state.name, 
            emailId: this.state.emailId,
            mobilenum:this.state.mobilenum,
            gender:this.state.gender,
            state:this.state.state,
            skills:skillsas.toString()};        
            console.log('user => ' + JSON.stringify(user));
            UserService.updateUser(user,this.state.id).then((res)=>{
            this.props.history.push('/users');
        })
         
    }

    componentDidMount(){
        UserService.getUserById(this.state.id).then((res)=>{
            let user = res.data;
            var arrayStr=user.skills.split(",");
            console.log(arrayStr);
            
            this.setState({name:user.name,
                emailId:user.emailId,
                mobilenum:user.mobilenum,
                state:user.state,
                gender:user.gender,
                skills:{
                    Java:arrayStr.indexOf("Java")>-1?true:false,
                    SpringBoot:arrayStr.indexOf("SpringBoot")>-1?true:false,
                    JavaScript:arrayStr.indexOf("JavaScript")>-1?true:false,
                    MySQL:arrayStr.indexOf("MySQL")>-1?true:false
                }


            })
        })
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
        this.setState({gender:event.target.value})
    }



    render() {
        return (
            <div>
                <br></br>
                <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update User</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Full Name: </label>
                                            <input placeholder="First Name" name="name" className="form-control" 
                                                value={this.state.name} onChange={this.changeNameHandler}/>
                                            </div>
                                        
                                        <div className = "form-group ">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
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
                                            <input type="File" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div> */}

                                        <button className="btn btn-success" onClick={this.updateUser}>Save</button>
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

export default UpdateUserComponent;