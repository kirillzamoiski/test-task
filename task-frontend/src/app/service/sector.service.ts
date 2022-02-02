import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sector} from "../model/sector";
import {ProfileInfo} from "../model/profile-info";

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  private baseURLSector = "http://localhost:8080/api/sectors";

  constructor(private httpClient: HttpClient) { }

  getSectorList(): Observable<Sector[]> {
    return this.httpClient.get<Sector[]>(`${this.baseURLSector}`);
  }
}
