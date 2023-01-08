import path from 'path';
import { pathsToModuleNameMapper } from 'ts-jest';
import { Config } from '@jest/types';
import { compilerOptions } from './tsconfig.json';

const config: Config.InitialOptions = {
    collectCoverageFrom: [
        '<rootDir>/src/**/*.ts',
    ],
    coverageDirectory: 'coverage',
    coveragePathIgnorePatterns: [
        '/coverage',
        '/dist',
        '/node_modules/',
        '/test',
    ],
    coverageReporters: [
        'lcov',
    ],
    coverageThreshold: {
        global: {
            branches: 95,
            functions: 95,
            lines: 95,
            statements: 95,
        },
    },
    globals: {
        'ts-jest': {
            tsconfig: path.resolve(__dirname, './tsconfig.eslint.json'),
        },
    },
    moduleFileExtensions: [
        'ts',
        'js',
    ],
    // eslint-disable-next-line @typescript-eslint/no-unsafe-argument,@typescript-eslint/no-unsafe-member-access
    moduleNameMapper: pathsToModuleNameMapper(compilerOptions.paths, { prefix: '<rootDir>/' }),
    preset: 'ts-jest',
    roots: [
        '<rootDir>/test/',
    ],
    setupFiles: [
        './test/jest.setup.ts',
    ],
    testEnvironment: 'node',
};

export default config;
