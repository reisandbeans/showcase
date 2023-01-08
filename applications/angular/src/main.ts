import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { BrowserRootModule } from '@modules/root/browser-root.module';
import { environment } from '@environment';

const { production } = environment;

if (production) {
    enableProdMode();
}

document.addEventListener('DOMContentLoaded', () => {
    void platformBrowserDynamic().bootstrapModule(BrowserRootModule);
});
