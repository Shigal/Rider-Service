// import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
// import authService from '../services/authService';

// const user = JSON.parse(localStorage.getItem('user'));

// const authSlice = createSlice({
//     name: 'auth',
//     initialState,
//     extraReducers: {
//         [register.fulfilled]: (state, action) => {
//             state.isLoggedIn = false;
//           },
//           [register.rejected]: (state, action) => {
//             state.isLoggedIn = false;
//           },
//           [login.fulfilled]: (state, action) => {
//             state.isLoggedIn = true;
//             state.user = action.payload.user;
//           },
//           [login.rejected]: (state, action) => {
//             state.isLoggedIn = false;
//             state.user = null;
//           },
//           [logout.fulfilled]: (state, action) => {
//             state.isLoggedIn = false;
//             state.user = null;
//           }
//     }
// });

// export const userRegister = createAsyncThunk('auth/signup',
// async({username, email, password}, thunkAPI) => {
//     try {
//         const response = await authService.userRegister(username, email, password);
//         thunkAPI.dispatch(setMessage(response.data.message));
//         return response.data;
//     } catch (error) {
//         const message = (error.response && error.response.data && error.response.data.message) ||
//         error.message ||
//         error.toString()
//         thunkAPI.dispatch(setMessage(message));
//         return thunkAPI.rejectWithValue();
//     }
// }
// );

// export const userLogin = createAsyncThunk('auth/signin', 
// async({username, password}, thunkAPI) => {
//     try {
//         const data = await authService.userLogin(username, password);
//         return {user: data};
//     } catch (error) {
//         const message = (error.response && error.response.data && error.response.data.message) ||
//         error.message ||
//         error.toString()
//         thunkAPI.dispatch(setMessage(message));
//         return thunkAPI.rejectWithValue();
//     }
// }
// )

// export const userLogout = createAsyncThunk('auth/logout', async () => {
//     await authService.useLogout();
// })


// const {authReducer} = authSlice;
// export default authReducer;