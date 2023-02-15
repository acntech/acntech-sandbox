import {UserInfo} from "../types/user-info";

export interface State<T> {
    loading: boolean,
    data?: T,
    error?: string
}

export interface Action<T> {
    type: 'LOADING' | 'SUCCESS' | 'FAILED',
    data?: T
}

export const userInfoInitialState: State<UserInfo> = {
    loading: true
}

export const userInfoReducer = (state: State<UserInfo>, action: Action<UserInfo>): State<UserInfo> => {
    const {type, data} = action;
    switch (type) {
        case 'SUCCESS':
            return {
                loading: false,
                data
            }
        case 'FAILED':
            return {
                loading: false,
                error: 'BOOM!'
            }
        default:
            return state
    }
};