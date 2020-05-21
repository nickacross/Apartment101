import { Application } from './application';

export class Apartment {
    aptType: string; // apt_type 1B1Bath, 2B1Bath, 2B2Bath
    noOfRooms: number;
    noOfBaths: number;
    aptNo: number;
    aptLevel: number;
    typeOfFlooring: string; // Laminate, Carpet, Wood, Tile, Linoleum
    availability: number; // 0 = false, 1 = true
    appList: Application[];
}