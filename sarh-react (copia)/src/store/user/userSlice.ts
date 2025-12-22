import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import { UserWithId } from "../../interfaces/interfaces.d";
//import { AUTH_ACTION_TYPES } from "../../constants/AuthConstants";

//import { authReducer } from "../../context/authReducer";

const DEFAULT_STATE = 
  {
    id: "",
    username: "",
    password: "",
    accessToken: "",
    isLogged: false,
    roles: []    
  }
  


//const UPDATE_STATE_BY_ACTION = {

//}

const initialState: UserWithId = (() => {
  return DEFAULT_STATE;
})();

const userSlice = createSlice({
  name: "users",
  initialState,
  reducers: {
    setUsername: (state, action: PayloadAction<string>
    ) => {
      const username = action.payload;
      const newState = { ...state, username: username };
      return newState;
    },
    setPassword: (state,action: PayloadAction<string>
    ) => {
      const newState = { ...state, password: action.payload };
      return newState;
    },
    setRoles: (state, action: PayloadAction<string[]>) => {
      const { payload } = action;
      const newState = { ...state, roles: payload };
      return newState;
    },
    setAccessToken: (state,action: PayloadAction<string>
    ) => {
      const { payload } = action;
      const newState = { ...state, accessToken: payload };
      return newState;
    },
     setLogin : (state,action: PayloadAction<boolean>) => {
      const { payload } = action;
      const newState = { ...state, isLogged: payload };
      return newState;
    },
     setLogout: (state,action: PayloadAction<boolean>) => {
      const { payload } = action;
      const newState = { ...state, isLogged: payload };
      return newState;
    },
  },
});

export default userSlice.reducer;
export const { setUsername, setPassword, setRoles, setAccessToken, setLogin, setLogout } = userSlice.actions;