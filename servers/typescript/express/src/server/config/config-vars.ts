export enum EnvVars {
    NodeEnv = 'NODE_ENV',
    ServerPort = 'SERVER_PORT',
    ServerShutDownHandler = 'SERVER_USE_SHUTDOWN_HANDLER',
}

export enum ArgVars {
    ServerPort = 'serverPort',
    ServerShutDownHandler = 'serverShutdownHandler',
}

export enum ServerConfigVars {
    DefaultLanguage = 'defaultLanguage',
    DistFolderPath = 'distFolderPath',
    IsProduction = 'isProduction',
    ServerPort = 'serverPort',
    ServerShutDownHandler = 'serverShutdownHandler',
    SupportedLanguages = 'supportedLanguages',
}

export interface EnvConfig {
    [EnvVars.NodeEnv]?: string;
    [EnvVars.ServerPort]?: string;
    [EnvVars.ServerShutDownHandler]?: string;
}

export interface ArgConfig {
    [ArgVars.ServerPort]?: number;
    [ArgVars.ServerShutDownHandler]?: boolean;
}

export interface ServerConfig {
    [ServerConfigVars.DefaultLanguage]: string;
    [ServerConfigVars.DistFolderPath]: string;
    [ServerConfigVars.IsProduction]: boolean;
    [ServerConfigVars.ServerPort]: number;
    [ServerConfigVars.ServerShutDownHandler]: boolean;
    [ServerConfigVars.SupportedLanguages]: string[];
}
