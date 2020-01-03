
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AmComponent } from './am.component';
// import { AuthGuard } from '../authentication/guard/auth.guard';

const routes: Routes = [
  {
    path: '',component :AmComponent,
    // canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', loadChildren: './common/shared/home/home.module#HomeModule'  },
      //categories
      { path: 'amphitheater', loadChildren: './categories/amphitheater/amphitheater.module#AmphitheaterModule' },
      { path: 'classroom', loadChildren: './categories/classroom/classroom.module#ClassroomModule' },
      { path: 'device', loadChildren: './categories/device/device.module#DeviceModule' },
      { path: 'StaffIncharge', loadChildren: './categories/staff-incharge/staff-incharge.module#StaffInchargeModule' }
    ]

  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AmRoutingModule { }
