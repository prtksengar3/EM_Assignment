import axios from 'axios';

const base_url = "http://localhost:8090/api/v1/users";

class UserService{
    getUsers(){
        return axios.get(base_url);
    }

    createUser(user){
        return axios.post(base_url,user);
    }

    getUserById(userId){
        return axios.get(base_url+"/"+userId);
    }

    updateUser(user,userId){
        return axios.put(base_url+"/"+userId,user);
    }
}

export default new UserService()