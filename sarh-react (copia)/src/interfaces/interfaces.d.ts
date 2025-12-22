
export type UserId = string;

export interface User {
    username: string;
    password: string;
    accessToken: string;
    isLogged: boolean;
    roles: string[];
        
}

export interface UserState extends User {
    accessToken: string;
    isLogged: boolean;
    error: string;
}

export interface UserWithId extends User {
    
    id: UserId;
}

export interface AuthAction {

    type: string,
    payload: User
}
