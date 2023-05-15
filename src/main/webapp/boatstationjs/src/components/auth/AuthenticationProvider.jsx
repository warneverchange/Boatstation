import {createContext, useCallback, useContext, useEffect, useState} from "react";
import {useFetching} from "../../hooks/useFetching";
import axios from "axios";
import { URL, setAxiosJwtToken } from "../../queries/RequestConfig";
import {useNavigate} from "react-router-dom";
import jwtDecode from "jwt-decode";

const AuthenticationContext = createContext()
export const useAuthenticationContext = () => {
    return useContext(AuthenticationContext)
}

const _authenticate = async (authData) => {
    return await axios.post(`${URL}/api/auth/authenticate`, {
        username: authData["username"],
        password: authData["password"]
    });
}

const _register = async (regData) => {
    return await axios.post(`${URL}/api/auth/register`, {
        firstName: regData["firstName"],
        lastName: regData["lastName"],
        patronymic: regData["patronymic"],
        passportData: regData["passportData"],
        phoneNumber: regData["phoneNumber"],
        username: regData["username"],
        password: regData["password"],
        email: regData["email"]
    });
}

export const AuthenticationProvider = (
    {
        children
    }) => {
    const navigate = useNavigate()
    const [isAdmin, setIsAdmin] = useState(false)
    const [token, setToken] = useState("")

    const [authenticate, isAuthReqLoading, authErr] = useFetching(
        async (authData) => {
            const response = await _authenticate(authData);
            if (response.data.token !== null &&
                response.data.token !== undefined &&
                response.data.token !== '') {
                setIsAdmin(jwtDecode(response.data.token)["is_admin"]);
            }
            setAxiosJwtToken(response.data.token)
            setToken(response.data.token);
        }
    )

    const [register, isRegReqLoading, regErr] = useFetching(
        async (regData) => {
            const response = await _register(regData)
            if (response.data.token !== null &&
                response.data.token !== undefined &&
                response.data.token !== '') {
                setIsAdmin(jwtDecode(response.data.token)["is_admin"])
            }
            setAxiosJwtToken(response.data.token)
            setToken(response.data.token)
        }
    )

    const logout = useCallback(() => {
        setToken('');
    })

    useEffect(() => {
        if (isAuthReqLoading || isRegReqLoading) {
            navigate("/loading")
        } else if (token.length === 0) {
            navigate("/auth")
        } else {
            navigate("/")
        }
    }, [token, isAuthReqLoading, isRegReqLoading])



    return (
        <AuthenticationContext.Provider value={{
            authenticate: authenticate,
            register: register,
            err: {...authErr, ...regErr},
            isAdmin: isAdmin,
            logout: logout
        }}>
            {children}
        </AuthenticationContext.Provider>
    )
}

