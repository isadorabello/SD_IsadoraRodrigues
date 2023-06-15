import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { ProdutoDTO } from '../dtos/produto.dto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private client: HttpClient) { }

  public findAll(): Observable<ProdutoDTO[]> {
    return this.client.get<ProdutoDTO[]>('http://localhost:8081/produto');
  }

}
