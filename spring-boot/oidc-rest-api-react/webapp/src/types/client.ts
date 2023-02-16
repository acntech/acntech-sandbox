export interface ClientError {
    status: number,
    error: string,
    message: string,
}

export interface ClientResponse<T> {
    status: number,
    data?: T,
    error?: ClientError
}