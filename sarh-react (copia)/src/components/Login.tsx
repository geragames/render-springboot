//import { useLocation, useNavigate } from "react-router";
import { useEffect, useRef, useState } from "react";
import axios from "../api/axios";
import { useAppSelector } from "../hooks/store";
import { useUserActions } from "../hooks/useUserActions";
import { useLocation, useNavigate } from "react-router-dom";



const LOGIN_URL = "/auth/log-in";

export function Login() {

    const user = useAppSelector((state) => state.users);

    const { usernameToUser, passwordToUser, accessTokenByUser, loadRolesByUser } = useUserActions();

    const navigate = useNavigate();

    const location = useLocation();

    const from = location.state?.from?.pathname || "/";
    
    const userRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);
    const errRef = useRef<HTMLParagraphElement>(null);

    const [errMsg, setErrMsg] = useState("");

    useEffect(() => {
        setErrMsg("");
    }, [user]);

    const handleChangeUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
        usernameToUser(event.target.value);

    }

    const handleChangePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
        passwordToUser(event.target.value);
    }
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const response = await axios.post(LOGIN_URL,
                JSON.stringify({
                    username: user.username,
                    password: user.password
                }),
                {
                    headers: { "Content-Type": "application/json" },
                    withCredentials: true
                }
            );

            console.log(JSON.stringify(response?.data));
            accessTokenByUser(response?.data.jwt);
            loadRolesByUser(response?.data?.roles);

            console.log("response", response.data);

            navigate(from, { replace: true});


        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="login-page">
            <div className="form">
            <p ref={errRef} >{errMsg}</p>
            <h1>Sign In</h1>

            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Usuario:</label>
                <input
                    type="text"
                    id="username"
                    ref={userRef}
                    autoComplete="false"
                    onChange={handleChangeUsername}
                    value={user.username}
                    placeholder="&#xf007; username"
                />
                
                <input
                    type="password"
                    id="password"
                    ref={passwordRef}
                    onChange={handleChangePassword}
                    value={user.password}
                    placeholder="&#xf023; password"
                    required
                />

                <button>Sign In</button>
            </form>
            </div>
        </div>)
        
}
