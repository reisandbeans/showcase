/* eslint-disable @typescript-eslint/no-unsafe-return */
/* eslint-disable @typescript-eslint/no-unsafe-assignment */

const mock = () => {
    let storage: Record<string, any> = {};

    return {
        getItem: (key: string) => storage[key] ? storage[key] : null,
        setItem: (key: string, value: any) => storage[key] = value || '',
        removeItem: (key: string) => delete storage[key],
        clear: () => storage = {},
    };
};

Object.defineProperty(window, 'open', { value: () => {} });
Object.defineProperty(window, 'localStorage', { value: mock() });
Object.defineProperty(window, 'sessionStorage', { value: mock() });
Object.defineProperty(window, 'getComputedStyle', {
    value: () => ({
        getPropertyValue: () => {
            return '';
        },
    }),
});
