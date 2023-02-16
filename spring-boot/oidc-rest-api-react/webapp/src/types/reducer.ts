export interface State<T> {
    loading: boolean,
    data?: T,
    error?: string
}

export interface Action<T> {
    type: 'LOADING' | 'SUCCESS' | 'FAILED',
    data?: T
}