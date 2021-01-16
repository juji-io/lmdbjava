package org.lmdbjava;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.nativeimage.c.type.CLongPointer;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.Pointer;
import org.graalvm.word.PointerBase;

/**
 * Bindings to liblmdb.
 */
@CContext(NativeMain.Directives.class)
final class NativeLibrary {

    @CStruct("MDB_envinfo")
    interface MDB_envinfo extends PointerBase {

        @CField("me_mapaddr")
        VoidPointer me_mapaddr();

        @CField("me_mapsize")
        long me_mapsize();

        @CField("me_last_pgno")
        long me_last_pgno();

        @CField("me_last_txnid")
        long me_last_txnid();

        @CField("me_maxreaders")
        int me_maxreaders();

        @CField("me_numreaders")
        int me_numreaders();

    }

    @CStruct("MDB_stat")
    interface MDB_stat extends PointerBase {

        @CField("ms_psize")
        int ms_psize();

        @CField("ms_depth")
        int ms_depth();

        @CField("ms_branch_pages")
        long ms_branch_pages();

        @CField("ms_leaf_pages")
        long ms_leaf_pages();

        @CField("ms_overflow_pages")
        long ms_overflow_pages();

        @CField("ms_entries")
        long ms_entries();

    }


    /**
     * Custom comparator callback used by <code>mdb_set_compare</code>.
     */
    public interface ComparatorCallback extends CFunctionPointer {

        @InvokeCFunctionPointer
        int compare(Pointer keyA, Pointer keyB);

    }


    @CFunction("mdb_cursor_close")
    static native void mdb_cursor_close(Pointer cursor);

    @CFunction("mdb_cursor_count")
    static native int mdb_cursor_count(Pointer cursor, CLongPointer countp);

    @CFunction("mdb_cursor_del")
    static native int mdb_cursor_del(Pointer cursor, int flags);

    @CFunction("mdb_cursor_get")
    static native int mdb_cursor_get(Pointer cursor, Pointer k, Pointer v,
                                     int cursorOp);

    @CFunction("mdb_cursor_open")
    static native int mdb_cursor_open(Pointer txn, Pointer dbi,
                                      WordPointer cursorPtr);

    @CFunction("mdb_cursor_put")
    static native int mdb_cursor_put(Pointer cursor, Pointer key, Pointer data,
                                      int flags);

    @CFunction("mdb_cursor_renew")
    static native int mdb_cursor_renew(Pointer txn, Pointer cursor);

    @CFunction("mdb_dbi_close")
    static native void mdb_dbi_close(Pointer env, Pointer dbi);

    @CFunction("mdb_dbi_flags")
    static native int mdb_dbi_flags(Pointer txn, Pointer dbi,
                                    CIntPointer flags);

    @CFunction("mdb_dbi_open")
    static native int mdb_dbi_open(Pointer txn, CCharPointer name, int flags,
                                   Pointer dbi);

    @CFunction("mdb_del")
    static native int mdb_del(Pointer txn, Pointer dbi, Pointer key,
                              Pointer data);

    @CFunction("mdb_drop")
    static native int mdb_drop(Pointer txn, Pointer dbi, int del);

    @CFunction("mdb_env_close")
    static native void mdb_env_close(Pointer env);

    @CFunction("mdb_env_copy2")
    static native int mdb_env_copy2(Pointer env, CCharPointer path, int flags);

    @CFunction("mdb_env_create")
    static native int mdb_env_create(WordPointer envPtr);

    @CFunction("mdb_env_get_fd")
    static native int mdb_env_get_fd(Pointer env, Pointer fd);

    @CFunction("mdb_env_get_flags")
    static native int mdb_env_get_flags(Pointer env, int flags);

    @CFunction("mdb_env_get_maxkeysize")
    static native int mdb_env_get_maxkeysize(Pointer env);

    @CFunction("mdb_env_get_maxreaders")
    static native int mdb_env_get_maxreaders(Pointer env, int readers);

    @CFunction("mdb_env_get_path")
    static native int mdb_env_get_path(Pointer env, CCharPointer path);

    @CFunction("mdb_env_info")
    static native int mdb_env_info(Pointer env, MDB_envinfo info);

    @CFunction("mdb_env_open")
    static native int mdb_env_open(Pointer env, CCharPointer path, int flags,
                                   int mode);

    @CFunction("mdb_env_set_flags")
    static native int mdb_env_set_flags(Pointer env, int flags, int onoff);

    @CFunction("mdb_env_set_mapsize")
    static native int mdb_env_set_mapsize(Pointer env, long size);

    @CFunction("mdb_env_set_maxdbs")
    static native int mdb_env_set_maxdbs(Pointer env, int dbs);

    @CFunction("mdb_env_set_maxreaders")
    static native int mdb_env_set_maxreaders(Pointer env, int readers);

    @CFunction("mdb_env_stat")
    static native int mdb_env_stat(Pointer env, MDB_stat stat);

    @CFunction("mdb_env_sync")
    static native int mdb_env_sync(Pointer env, int f);

    @CFunction("mdb_get")
    static native int mdb_get(Pointer txn, Pointer dbi, Pointer key,
                              Pointer data);

    @CFunction("mdb_put")
    static native int mdb_get(Pointer txn, Pointer dbi, Pointer key,
                              Pointer data, int flags);

    @CFunction("mdb_reader_check")
    static native int mdb_reader_check(Pointer env, int dead);

    @CFunction("mdb_set_compare")
    static native int mdb_set_compare(Pointer txn, Pointer dbi,
                                      ComparatorCallback cb);

    @CFunction("mdb_stat")
    static native int mdb_stat(Pointer txn, Pointer dbi, MDB_stat stat);

    @CFunction("mdb_strerror")
    static native CCharPointer mdb_strerror(int rc);

    @CFunction("mdb_txn_abort")
    static native void mdb_txn_abort(Pointer txn);

    @CFunction("mdb_txn_begin")
    static native int mdb_txn_begin(Pointer env, Pointer parentTx, int flags,
                                    WordPointer txPtr);

    @CFunction("mdb_txn_commit")
    static native int mdb_txn_commit(Pointer txn);

    @CFunction("mdb_txn_env")
    static native Pointer mdb_txn_env(Pointer txn);

    @CFunction("mdb_txn_id")
    static native long mdb_txn_id(Pointer txn);

    @CFunction("mdb_txn_renew")
    static native int mdb_txn_renew(Pointer txn);

    @CFunction("mdb_txn_reset")
    static native void mdb_txn_reset(Pointer txn);

    @CFunction("mdb_version")
    static native CCharPointer mdb_version(CIntPointer major, CIntPointer minor,
                                      CIntPointer patch);

}
