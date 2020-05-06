import React from 'react';
import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16'
import renderer from 'react-test-renderer';
import {WorkoutsTable} from "./WorkoutsTable";

configure({adapter: new Adapter()});

describe('WorkoutsTable component testing', () => {
    it('render correctly WorkoutsTable component', () => {
        const trainer = {
            id: 1,
            name: 'Tom',
            workouts: [{
                id: 1,
                name: 'wo1'
            }]
        };
        const wrapper = renderer.create(<WorkoutsTable trainer={trainer}/>).toJSON();

        expect(wrapper).toMatchSnapshot();
    });

    it('should render header', () => {
        const trainer = {
            id: 1,
            name: 'Tom',
            workouts: [{
                id: 1,
                name: 'wo1'
            }]
        };

        const wrapper = shallow(<WorkoutsTable trainer={trainer}/>);

        const header = <h3>Workouts of <span style={{color: "red"}}>{trainer.name}</span></h3>;

        expect(wrapper.contains(header)).toBe(true)
    });

    it('should render workouts table with 2 records', () => {
        const trainer = {
            id: 1,
            name: 'Tom',
            workouts: [
                {
                    id: 1,
                    name: 'wo1'
                },
                {
                    id: 2,
                    name: 'wo2'
                }
            ]
        };

        const wrapper = shallow(<WorkoutsTable trainer={trainer}/>);

        const table = wrapper.find('table');
        expect(table).toHaveLength(1);

        const tbody = table.find('tbody');
        expect(tbody).toHaveLength(1);

        const rows = tbody.find('tr');
        expect(rows).toHaveLength(trainer.workouts.length);
    });
});