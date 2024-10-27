module.exports = {
    env: {
        es6: true,
        'jest/globals': true,
        node: true,
    },
    ignorePatterns: ['dist', 'coverage'],
    overrides: [
        {
            extends: [
                'plugin:@typescript-eslint/recommended',
                'plugin:@typescript-eslint/recommended-requiring-type-checking',
                'plugin:jest/recommended',
            ],
            files: ['*.ts', '*.tsx'],
            parser: '@typescript-eslint/parser',
            parserOptions: {
                project: './tsconfig.eslint.json',
                tsconfigRootDir: __dirname,
            },
            plugins: [
                '@typescript-eslint',
                'typescript-sort-keys',
                'jest',
            ],
            rules: {
                '@typescript-eslint/array-type': 'off',
                '@typescript-eslint/ban-types': [
                    'error',
                    {
                        types: {
                            // eslint-disable-next-line id-blacklist
                            Boolean: {
                                message: 'Avoid using the `Boolean` type. Did you mean `boolean`?',
                            },
                            Function: {
                                message: 'Avoid using the `Function` type. Prefer a specific function type, like ' +
                                    '`() => void`.',
                            },
                            // eslint-disable-next-line id-blacklist
                            Number: {
                                message: 'Avoid using the `Number` type. Did you mean `number`?',
                            },
                            Object: {
                                message: 'Avoid using the `Object` type. Did you mean `object`?',
                            },
                            // eslint-disable-next-line id-blacklist
                            String: {
                                message: 'Avoid using the `String` type. Did you mean `string`?',
                            },
                            Symbol: {
                                message: 'Avoid using the `Symbol` type. Did you mean `symbol`?',
                            },
                        },
                    },
                ],
                '@typescript-eslint/comma-dangle': [
                    'error',
                    'always-multiline',
                ],
                '@typescript-eslint/explicit-function-return-type': 'off',
                '@typescript-eslint/indent': [
                    'error',
                    4,
                    {
                        FunctionDeclaration: {
                            parameters: 'first',
                        },
                        FunctionExpression: {
                            parameters: 'first',
                        },
                        ignoredNodes: ['TSTypeParameterInstantiation'],
                    },
                ],
                '@typescript-eslint/member-delimiter-style': [
                    'error',
                    {
                        multiline: {
                            delimiter: 'semi',
                            requireLast: true,
                        },
                        singleline: {
                            delimiter: 'semi',
                            requireLast: false,
                        },
                    },
                ],
                "@typescript-eslint/member-ordering": [
                    "error",
                    {
                        default: {
                            memberTypes: [
                                // Index signature
                                "signature",
                                "call-signature",

                                // Fields
                                "public-static-field",
                                "protected-static-field",
                                "private-static-field",
                                "#private-static-field",

                                "public-decorated-field",
                                "protected-decorated-field",
                                "private-decorated-field",

                                "public-instance-field",
                                "protected-instance-field",
                                "private-instance-field",
                                "#private-instance-field",

                                "public-abstract-field",
                                "protected-abstract-field",

                                "public-field",
                                "protected-field",
                                "private-field",
                                "#private-field",

                                "static-field",
                                "instance-field",
                                "abstract-field",

                                "decorated-field",

                                "field",

                                // Static initialization
                                "static-initialization",

                                // Constructors
                                "public-constructor",
                                "protected-constructor",
                                "private-constructor",

                                "constructor",

                                // Getters
                                "public-static-get",
                                "protected-static-get",
                                "private-static-get",
                                "#private-static-get",

                                "public-decorated-get",
                                "protected-decorated-get",
                                "private-decorated-get",

                                "public-instance-get",
                                "protected-instance-get",
                                "private-instance-get",
                                "#private-instance-get",

                                "public-abstract-get",
                                "protected-abstract-get",

                                "public-get",
                                "protected-get",
                                "private-get",
                                "#private-get",

                                "static-get",
                                "instance-get",
                                "abstract-get",

                                "decorated-get",

                                "get",

                                // Setters
                                "public-static-set",
                                "protected-static-set",
                                "private-static-set",
                                "#private-static-set",

                                "public-decorated-set",
                                "protected-decorated-set",
                                "private-decorated-set",

                                "public-instance-set",
                                "protected-instance-set",
                                "private-instance-set",
                                "#private-instance-set",

                                "public-abstract-set",
                                "protected-abstract-set",

                                "public-set",
                                "protected-set",
                                "private-set",
                                "#private-set",

                                "static-set",
                                "instance-set",
                                "abstract-set",

                                "decorated-set",

                                "set",

                                // Methods
                                "public-static-method",
                                "protected-static-method",
                                "private-static-method",
                                "#private-static-method",

                                "public-decorated-method",
                                "protected-decorated-method",
                                "private-decorated-method",

                                "public-instance-method",
                                "protected-instance-method",
                                "private-instance-method",
                                "#private-instance-method",

                                "public-abstract-method",
                                "protected-abstract-method",

                                "public-method",
                                "protected-method",
                                "private-method",
                                "#private-method",

                                "static-method",
                                "instance-method",
                                "abstract-method",

                                "decorated-method",

                                "method",
                            ],
                            order: "alphabetically",
                        },
                    },
                ],
                '@typescript-eslint/no-empty-function': 'off',
                '@typescript-eslint/no-empty-interface': 'off',
                '@typescript-eslint/no-explicit-any': 'off',
                '@typescript-eslint/no-non-null-assertion': 'error',
                '@typescript-eslint/no-parameter-properties': 'off',
                '@typescript-eslint/no-shadow': 'error',
                '@typescript-eslint/no-unsafe-assignment': 'off',
                '@typescript-eslint/no-unsafe-call': 'off',
                '@typescript-eslint/no-unsafe-member-access': 'off',
                '@typescript-eslint/no-unsafe-return': 'off',
                '@typescript-eslint/no-unused-expressions': 'error',
                '@typescript-eslint/no-use-before-define': 'off',
                '@typescript-eslint/no-useless-constructor': 'error',
                '@typescript-eslint/no-var-requires': 'off',
                '@typescript-eslint/prefer-for-of': 'error',
                '@typescript-eslint/prefer-function-type': 'error',
                '@typescript-eslint/quotes': [
                    'error',
                    'single',
                ],
                '@typescript-eslint/restrict-template-expressions': 'off',
                '@typescript-eslint/semi': [
                    'error',
                    'always',
                ],
                '@typescript-eslint/triple-slash-reference': [
                    'error',
                    {
                        lib: 'always',
                        path: 'always',
                        types: 'prefer-import',
                    },
                ],
                '@typescript-eslint/unbound-method': 'off',
                '@typescript-eslint/unified-signatures': 'error',
                indent: 'off',
                'jest/no-conditional-expect': 'off',
                'jest/no-try-expect': 'off',
                'jsdoc/no-types': 'error',
                'no-shadow': 'off',
                'no-use-before-define': 'off',
                'no-void': 'off',
                'typescript-sort-keys/interface': 'off',
                'typescript-sort-keys/string-enum': 'error',
            },
        },
    ],
    plugins: [
        'eslint-plugin-jsdoc',
        'eslint-plugin-import',
    ],
    root: true,
    rules: {
        'arrow-body-style': 'off',
        camelcase: 'error',
        'comma-dangle': ['error', 'always-multiline'],
        complexity: 'off',
        'constructor-super': 'error',
        curly: 'error',
        'eol-last': 'error',
        eqeqeq: [
            'error',
            'smart',
        ],
        'guard-for-in': 'error',
        'id-blacklist': [
            'error',
            'any',
            'Number',
            'number',
            'String',
            'string',
            'Boolean',
            'boolean',
            'Undefined',
            'undefined',
        ],
        'id-match': 'error',
        'import/no-deprecated': 'warn',
        'jsdoc/check-alignment': 'error',
        'jsdoc/check-indentation': 'error',
        'jsdoc/newline-after-description': 'error',
        'jsdoc/no-types': 'error',
        'max-classes-per-file': 'off',
        'max-len': [
            'error',
            {
                code: 120,
                ignorePattern: '^import |^export |^require (.*?)$',
            },
        ],
        'new-parens': 'error',
        'no-bitwise': 'error',
        'no-caller': 'error',
        'no-cond-assign': 'error',
        'no-console': 'error',
        'no-debugger': 'error',
        'no-empty': 'off',
        'no-eval': 'error',
        'no-fallthrough': 'error',
        'no-invalid-this': 'off',
        'no-new-wrappers': 'error',
        'no-restricted-imports': [
            'error',
            'rxjs/Rx',
        ],
        'no-throw-literal': 'error',
        'no-trailing-spaces': 'error',
        'no-undef-init': 'error',
        'no-underscore-dangle': 'error',
        'no-unsafe-finally': 'error',
        'no-unused-labels': 'error',
        'object-shorthand': 'error',
        'one-var': [
            'error',
            'never',
        ],
        'prefer-arrow-callback': 'error',
        'quote-props': [
            'error',
            'as-needed',
        ],
        radix: 'error',
        semi: 'error',
        'sort-keys': ['error', 'asc'],
        'spaced-comment': [
            'error',
            'always',
            {
                markers: [
                    '/',
                ],
            },
        ],
        'use-isnan': 'error',
        'valid-typeof': 'off',
    },
};
