import React from 'react';
import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16'
import {TrainersTable} from "./TrainersTable";
import renderer from 'react-test-renderer';

configure({adapter: new Adapter()});

describe('TrainersTable component testing', () => {
    it('render correctly TrainersTable component', () => {
        const trainers = [{
            id: 1,
            name: 'Tom'
        }];
        const wrapper = renderer.create(<TrainersTable trainers={trainers}/>).toJSON();

        expect(wrapper).toMatchSnapshot();
    });

    it('should render header', () => {
        const trainers = [{
            id: 1,
            name: 'Tom'
        }];
        const wrapper = shallow(<TrainersTable trainers={trainers}/>);
        expect(wrapper.contains(<h3>Trainers</h3>)).toBe(true)
    });

    it('should render trainers table with 1 record', () => {
        const trainers = [{
            id: 1,
            name: 'Tom'
        }];
        const wrapper = shallow(<TrainersTable trainers={trainers}/>);

        const table = wrapper.find('table');
        expect(table).toHaveLength(1);

        const tbody = table.find('tbody');
        expect(tbody).toHaveLength(1);

        const rows = tbody.find('tr');
        expect(rows).toHaveLength(trainers.length);
    });
});