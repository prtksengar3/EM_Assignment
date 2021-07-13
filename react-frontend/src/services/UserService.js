import axios from 'axios';

const base_url = "http://localhost:8081/api/v1/users";
const base_url1 = "http://localhost:8081/api/v1/pagedusers";


class UserService{
    getUsers(currentPage ,pageSize,sortBy){
        return axios.get(base_url,currentPage,pageSize,sortBy);
    }

    createUser(user){
        return axios.post(base_url,user);
    }

    getUserById(userId){
        console.log(userId)
        return axios.get(base_url+"/"+userId);
    }

    updateUser(user,userId){
        return axios.put(base_url+"/"+userId,user);
    }

}

export default new UserService()