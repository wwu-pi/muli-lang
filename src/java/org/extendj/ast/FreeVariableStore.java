package org.extendj.ast;

import java.util.ArrayList;
import java.util.Collection;

public class FreeVariableStore {

    static class FreeVariableEntry {
        int name_index;
        int index;
    }

    private ConstantPool constantPool;

    public Collection<FreeVariableEntry> freeVariables = new ArrayList<FreeVariableEntry>();

    public FreeVariableStore(ConstantPool cp) {
        this.constantPool = cp;
    }

    public FreeVariableStore addFreeVariableEntryAtCurrentPC(String name, int localNum) {
        System.out.println("name:"+name+",index:"+localNum);
        FreeVariableEntry e = new FreeVariableEntry();

        e.name_index = constantPool().addUtf8(name);
        e.index = localNum;

        freeVariables.add(e);
        return this;
    }

    public ConstantPool constantPool() {
        return constantPool;
    }
}
