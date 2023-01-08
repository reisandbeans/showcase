import { NgModule } from '@angular/core';
import { RouterModule } from "@angular/router";
import { BrowserModule } from "@angular/platform-browser";
import { environment } from '@environment';
import { RoutingModule } from './routing.module';

@NgModule({
    exports: [
            RouterModule,
    ],
    imports: [
        BrowserModule.withServerTransition({ appId: environment.appId }),
        RoutingModule
    ],
    providers: [],
})
export class RootModule {}
