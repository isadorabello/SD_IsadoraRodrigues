import { Component, OnInit } from '@angular/core';
import { ProdutoDTO } from 'src/app/dtos/produto.dto';

@Component({
  selector: 'app-produto-tabela',
  templateUrl: './produto-tabela.component.html',
  styleUrls: ['./produto-tabela.component.scss']
})
export class ProdutoTabelaComponent implements OnInit {

  produtos: ProdutoDTO[] = [];

  constructor() { }

  ngOnInit(): void {
    this.produtos = [
      {id: 1, descricao: 'Furadeira', preco: 800.00},
      {id: 2, descricao: 'Lixadeira', preco: 350.00},
      {id: 3, descricao: 'Serra Circular', preco: 500.00}
    ]
  }

}
