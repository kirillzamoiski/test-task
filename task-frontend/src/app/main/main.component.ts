import {Component, OnInit} from '@angular/core';
import {SectorService} from "../service/sector.service";
import {Sector} from "../model/sector";
import {ProfileInfo} from "../model/profile-info";
import {ProfileInfoService} from "../service/profile-info.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  sectorList: Sector[] = [];
  viewParentList: Sector[] = [];
  viewChildList: Sector[] = [];
  userInfo: ProfileInfo = new ProfileInfo();
  idProfile: number = -1;
  editable = false;

  constructor(private sectorService: SectorService, private profileInfoService: ProfileInfoService) {
  }

  ngOnInit(): void {
    this.sectorService.getSectorList().subscribe(data => {
        if (data) {
          this.sectorList = data;
          this.getParentSectors();
        }
      }
    )
  }

  saveProfile() {
    this.userInfo.sector = this.userInfo.sector.toString();
    if (this.idProfile == -1) {
      this.saveProfileInfo();
    } else {
      this.updateProfileInfo();
    }
  }

  private saveProfileInfo() {
    this.profileInfoService.saveProfileInfo(this.userInfo).subscribe(data => {
      this.userInfo = data;
      this.idProfile = this.userInfo.id;
    });
  }

  private updateProfileInfo() {
    this.profileInfoService.updateProfileInfo(this.idProfile, this.userInfo).subscribe(data => {
      this.userInfo = data;
    })
  }

  private getParentSectors(){
    for (const sector of this.sectorList) {
      if (sector.parentId == null) {
        this.viewParentList.push(sector);
      }
    }
  }

  public getChildList() {
    let parentId = 0;
    this.viewChildList = [];
    this.editable = true;
    for (const sector of this.sectorList) {
      if (sector.name == this.userInfo.sector) {
        parentId = sector.id;
      }
    }
    for (const sector of this.sectorList) {
      if (sector.parentId == parentId) {
        this.viewChildList.push(sector);
      }
    }
    if (this.viewChildList.length == 0) {
      this.editable = false;
    }
  }
}
