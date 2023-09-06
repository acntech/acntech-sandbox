import {Action, State, User} from "../types";

export const userInitialState: State<User> = {
    loading: true
}

export const userReducer = (state: State<User>, action: Action<User>): State<User> => {
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