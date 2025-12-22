import { setUsername, 
         setAccessToken,
         setLogin,setLogout, 
         setPassword,
         setRoles } from "../store/user/userSlice";

import { useAppDispatch } from "./store";

export const useUserActions = () => {
   const dispatch = useAppDispatch();

    const usernameToUser = (username: string) => {
        dispatch(setUsername(username));
    };
    const passwordToUser = (password: string) => {
        dispatch(setPassword(password));
    };
    const loadRolesByUser = (roles: string[]) => {
        dispatch(setRoles(roles));
    };
    const accessTokenByUser = (accessToken: string) => {
        dispatch(setAccessToken(accessToken));
    };

    const setUserLogin = (isLogged: boolean) => {
        dispatch(setLogin(isLogged));
    };

    const setUserLogout = (isLogged: boolean) => {
        dispatch(setLogout(isLogged));
    };

    return { usernameToUser ,passwordToUser , loadRolesByUser, accessTokenByUser, setUserLogin, setUserLogout };
}