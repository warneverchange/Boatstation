import axios from "axios"

export const URL = "http://localhost:8080"
export const _axios = axios.create()

export const setAxiosJwtToken = (token) => {
    _axios.defaults.headers.common["JWT"] = token 
}