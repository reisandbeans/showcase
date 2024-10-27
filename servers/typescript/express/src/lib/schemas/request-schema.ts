import { JSONSchemaType } from 'ajv';
import { GenericObject, RequestData } from '@lib/request.types';


export type RequestSchema<Params = GenericObject, Body = GenericObject, Query = GenericObject> =
    JSONSchemaType<RequestData<Params, Body, Query>>;

const defaultSchema: JSONSchemaType<GenericObject> = {
    properties: {},
    required: [],
    type: 'object',
};

export function createSchema<Params extends GenericObject, Body extends GenericObject, Query extends GenericObject>(
    paramsSchema: JSONSchemaType<Params>,
    bodySchema: JSONSchemaType<Body>,
    querySchema: JSONSchemaType<Query>,
): RequestSchema<Params, Body, Query> {
    const schema = {
        properties: {
            body: bodySchema as any,
            params: paramsSchema as any,
            query: querySchema as any,
        },
        required: [
            'body',
            'params',
            'query',
        ],
        type: 'object',
    };

    return schema as unknown as RequestSchema<Params, Body, Query>;
}

export function createQueryRequestSchema<Query extends GenericObject = GenericObject, Params extends GenericObject = GenericObject>(
    querySchema: JSONSchemaType<Query>,
    paramsSchema: JSONSchemaType<Params> = defaultSchema as any,
): RequestSchema<Params, GenericObject, Query> {
    return createSchema(paramsSchema, defaultSchema, querySchema);
}

export function createBodyRequestSchema<Body extends GenericObject = GenericObject, Params extends GenericObject = GenericObject>(
    bodySchema: JSONSchemaType<Body>,
    paramsSchema: JSONSchemaType<Params> = defaultSchema as any,
): RequestSchema<Params, Body> {
    return createSchema(paramsSchema, bodySchema, defaultSchema);
}

export function createParamsSchema<Params extends GenericObject = GenericObject>(
    paramsSchema: JSONSchemaType<Params>,
): RequestSchema<Params> {
    return createSchema(paramsSchema, defaultSchema, defaultSchema);
}
