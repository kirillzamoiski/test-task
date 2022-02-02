import { Injectable } from '@angular/core';
import {ProfileInfo} from "../model/profile-info";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProfileInfoService {

  private baseURLProfileInfo = "http://localhost:8080/api/profile-info";

  constructor(private httpClient: HttpClient) { }

  saveProfileInfo(profileInfo: ProfileInfo): Observable<ProfileInfo> {
    return this.httpClient.post<ProfileInfo>(`${this.baseURLProfileInfo}`,profileInfo);
  }

  updateProfileInfo(id: number, profileInfo: ProfileInfo): Observable<ProfileInfo> {
    return this.httpClient.put<ProfileInfo>(`${this.baseURLProfileInfo}/${id}`,profileInfo);
  }
}
