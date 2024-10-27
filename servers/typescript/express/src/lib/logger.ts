/* eslint-disable no-restricted-syntax */
/* eslint-disable no-console */

enum Severity {
    Debug = 'DEBUG',
    Error = 'ERROR',
    Info = 'INFO',
    Warn = 'WARN',
}

export interface LoggerType {
    child: (prefix: string) => LoggerType;
    debug: (...args: string[]) => void;
    error: (error: any, ...args: string[]) => void;
    info: (...args: string[]) => void;
    warn: (...args: string[]) => void;
}

export class Logger implements LoggerType {
    private prefixes: string[] = [];

    constructor(prefixes: string[]) {
        this.prefixes = this.prefixes.concat(prefixes);
    }

    child(prefix: string): Logger {
        const prefixes = this.prefixes.concat(prefix);
        return new Logger(prefixes);
    }

    debug(...args: string[]): void {
        const message = this.formatArgs(Severity.Debug, ...args);
        console.debug(message);
    }

    error(...args: string[]): void {
        const message = this.formatArgs(Severity.Error, ...args);
        console.error(message);
    }

    info(...args: string[]): void {
        const message = this.formatArgs(Severity.Info, ...args);
        console.info(message);
    }

    warn(...args: string[]): void {
        const message = this.formatArgs(Severity.Warn, ...args);
        console.warn(message);
    }

    private formatArgs(severity: Severity, ...args: string[]) {
        const timestamp = new Date().toISOString();
        const prefixes = this.prefixes.map(prefix => `[${prefix}]`).join(' ');
        return `[${timestamp}] [${severity}] ${prefixes}: ${args.join('\n')}`;
    }
}

export const logger = new Logger(['express-server']);

export function createLogger(prefix: string): Logger {
    return logger.child(prefix);
}
