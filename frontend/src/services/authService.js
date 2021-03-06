import axios from "axios";

const getToken = () => {
    return localStorage.getItem('USER_KEY');
}

export const userRegister = (authRequest) => {
    return axios({
        method: 'POST',
        url: `${process.env.hostUrl || 'http://localhost:8181'}/api/auth/signup`,
        data: authRequest
    })
}

export const userLogin = (authRequest) => {
    return axios({
        method: 'POST',
        url: `${process.env.hostUrl || 'http://localhost:8181'}/api/auth/signin`,
        data: authRequest
    })
}

export const fetchUserData=()=>{
    return axios({
        method:'GET',
        url:`${process.env.hostUrl||'http://localhost:8181'}/api/auth/profile`,
        headers:{
            'Authorization':getToken()
        }
    })
}

export const updateProfile = (profile,id) => {
    return axios({
        method:'PUT',
        url: `${process.env.hostUrl||'http://localhost:8181'}/api/auth/profile/${id}`,
        data: profile
    })
}

