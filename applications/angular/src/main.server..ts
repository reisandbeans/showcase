import { enableProdMode } from '@angular/core';
import { environment } from './environments/environment';

if (environment.production) {
    enableProdMode();
}

export { ServerRootModule } from './modules/root/server-root.module';
