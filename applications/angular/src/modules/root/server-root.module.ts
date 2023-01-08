import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';
import { RootModule } from './root.module';
import { RootComponent } from './components/root-component/root.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
    bootstrap: [
        RootComponent,
    ],
    declarations: [
        RootComponent,
    ],
    imports: [
        NoopAnimationsModule,
        RootModule,
        ServerModule,
    ],
})
export class ServerRootModule {}
