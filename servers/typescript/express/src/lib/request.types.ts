export type GenericObject = Record<string, any>;

export interface RequestParams<Params> {
    params: Params;
}

export interface RequestBody<Body = GenericObject, Params = GenericObject> extends RequestParams<Params> {
    body: Body;
}

export interface RequestQuery<Query = GenericObject, Params = GenericObject> extends RequestParams<Params>{
    query: Query;
}

export type RequestData<Params = GenericObject, Body = GenericObject, Query = GenericObject> =
    RequestBody<Body, Params> & RequestQuery<Query, Params>;
