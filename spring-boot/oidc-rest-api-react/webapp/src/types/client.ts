export interface ClientError {
    cause: string,
    redirectUrl: string
}

export interface ClientResponse<T> {
    status: number,
    data?: T,
    error?: ClientError
}