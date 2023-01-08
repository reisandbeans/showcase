import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RootModule } from './root.module';
import { RootComponent } from './components/root-component/root.component';

@NgModule({
    bootstrap: [
        RootComponent,
    ],
    declarations: [
        RootComponent,
    ],
    imports: [
        BrowserAnimationsModule,
        RootModule,
    ],
})
export class BrowserRootModule {}
