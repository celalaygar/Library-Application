import { Author } from '../author/Author';

export class Book {
    public id: number;
    public name: string;
    public publisher: string;
    public bookStatus: string;
    public content: string;
    public barcode: string;
    public author = new Author();
    constructor() { }
  }